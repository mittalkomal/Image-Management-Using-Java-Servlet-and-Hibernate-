package com.nagarro.imageManagement.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nagarro.imageManagement.Exceptions.CRUDFailedException;
import com.nagarro.imageManagement.Exceptions.ImageSizeExceededException;
import com.nagarro.imageManagement.Exceptions.TotalImageSizeExceededException;
import com.nagarro.imageManagement.constants.Constants;
import com.nagarro.imageManagement.model.Image;
import com.nagarro.imageManagement.service.ImageManagementService;
import com.nagarro.imageManagement.util.HibernateUtil;

public class ImageManagementImpl implements ImageManagementService {

	private Map<String, Double> usernameVsImageSize = new HashMap<>();

	@Override
	public Image saveImage(String username, Part part)
			throws IOException, ImageSizeExceededException, TotalImageSizeExceededException, CRUDFailedException {
		try {
			String fileName = getFileName(part);
			String filePath = Constants.IMAGE_LOCATION + "\\" + fileName;
			OutputStream outputStream = null;
			InputStream filecontent = null;
			try {
				outputStream = new FileOutputStream(new File(filePath));
				filecontent = part.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

			} catch (FileNotFoundException fne) {

			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}

			}

			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			Session session = sessFact.getCurrentSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			double imageSize = part.getSize() / 1024;
			if (imageSize > Constants.maxImageSizeInKB) {

				throw new ImageSizeExceededException("The size of image is exceeded so it  cannot be saved");
			}
			if (usernameVsImageSize.containsKey(username)) {
				if (imageSize + usernameVsImageSize.get(username) > Constants.maxTotalImageSizeInKB) {
					throw new TotalImageSizeExceededException("the total size of images excedded");
				}
			}
			Image image = new Image();
			image.setImagePath(filePath);
			image.setImgName(fileName);
			image.setUsername(username);
			image.setImgSize(imageSize);
			session.save(image);
			transaction.commit();
			System.out.println("Successfully inserted");
			return image;
		} catch (Exception e) {
			throw new CRUDFailedException("Error occured while saving image");
		}

	}

	private String getFileName(final Part part) {
		String filePath = "";
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				filePath = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		if (filePath.length() > 0) {
			int index = filePath.lastIndexOf("\\");
			String fileName = filePath.substring(index + 1);
			return fileName;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> getImageListForUser(String username) throws CRUDFailedException {
		try {

			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			Session session = sessFact.getCurrentSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Image where user_name=:username");
			query.setParameter("username", username);
			List<Image> imageList = query.list();
			Double usernameImgSize = 0.0;
			for (Image list : imageList)

			{
				usernameImgSize += list.getImgSize();
			}
			usernameVsImageSize.put(username, usernameImgSize);
			transaction.commit();
			return imageList;
		} catch (Exception e) {
			throw new CRUDFailedException("Error occured while fetching list");
		}
	}

	@Override
	public void deleteImage(String id) throws CRUDFailedException {
		try {
			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			Session session = sessFact.getCurrentSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Image where id=:id");
			query.setParameter("id", Integer.parseInt(id));
			Image image = (Image) query.uniqueResult();
			session.delete(image);
			transaction.commit();
		} catch (Exception e) {
			throw new CRUDFailedException("Error occured while deleting image from database");
		}
	}

	@Override
	public void updateImageName(String id, String newName) throws CRUDFailedException {
		try {
			SessionFactory sessFact = HibernateUtil.getSessionFactory();
			Session session = sessFact.getCurrentSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("from Image where id=:id");
			query.setParameter("id", Integer.parseInt(id));
			Image image = (Image) query.uniqueResult();
			image.setImgName(newName);
			session.update(image);
			transaction.commit();
		} catch (Exception e) {
			throw new CRUDFailedException("Error occured while updating image name");
		}

	}

	@Override
	public Double getTotalImageSizeForUsername(String username) {
		return usernameVsImageSize.get(username);
	}

}
