package org.fotoalbum.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "title cannot be empty")
	@Column(unique = true)
	private String title;

	@Column
	@Lob
	private String description;

	@NotEmpty(message = "Image url is incorrect")
	@Column
	private String imgUrl;

	@Column
	@NotEmpty
	private String tag;

	@Column(nullable = false)
	private boolean visible;

	@ManyToMany
	@JsonIgnore
	private List<Category> categories;

	public Photo() {
	}

	public Photo(String title, String description, String imgUrl, String tag, boolean visible,
			List<Category> categories) {

		setTitle(title);
		setDescription(description);
		setTitle(title);
		setImgUrl(imgUrl);
		setTag(tag);
		setVisible(visible);
		setCategories(categories);

	}

	public Photo(String title, String description, String imgUrl, String tag, boolean visible, Category category) {

		setTitle(title);
		setDescription(description);
		setTitle(title);
		setImgUrl(imgUrl);
		setTag(tag);
		setVisible(visible);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		boolean finded = false;
		for (Category cat : getCategories())
			if (cat.getId() == category.getId())
				finded = true;
		if (!finded)
			getCategories().add(category);
	}

	public void removeCategory(Category category) {
		getCategories().remove(category);
	}

	@Override
	public String toString() {
		return getId() + " - " + getTitle() + " - " + getDescription() + " - " + "tag: " + getTag() + " - "
				+ getImgUrl() + getCategories() + " - " + isVisible();
	}

}
