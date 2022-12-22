<template>
  <div>
    <header>
      <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
          <div class="d-flex justify-content-start mt-2">
            <a class="navbar-brand text-primary" href="#"
              ><img
                class="img-fluid logo"
                src=".././assets/img/bisan_logo.png"
                width="130px"
                height="130px"
                alt="logo"
            /></a>
          </div>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div
            class="collapse navbar-collapse d-flex justify-content-between"
            id="navbarSupportedContent"
          ></div>
        </div>
      </nav>
    </header>

    <div class="container">
      <div class="row">
        <div class="col d-flex flex-wrap mt-5">
          <div
            v-for="photo in photos"
            :key="photo.id"
            class="card mx-2 my-5"
            style="width: 19rem"
          >
            <img class="card-img-top" :src="photo.imgUrl" alt="image" />
            <div class="card-body">
              <h5 class="card-title fw-bold">{{ photo.title }}</h5>
              <p class="card-text">{{ photo.description }}</p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                <strong>tag:</strong> {{ photo.tag }}
              </li>
              <li class="list-group-item"><strong>categories:</strong></li>
            </ul>
            <div
              class="card-body"
              v-for="category in photo.categories"
              :key="category.id"
            >
              {{ category.name }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

const PHOTO_ID = -1;

export default {
  name: "Home",
  data() {
    return {
      apiUrl: "http://localhost:8080/api/1",
      photos: [],
      photoId: PHOTO_ID,
    };
  },
  methods: {
    getPhotos() {
      axios
        .get(this.apiUrl + "/photos/all")
        .then((res) => {
          const allPhotos = res.data;
          console.table(allPhotos);
          if (allPhotos == null) return;
          this.photos = allPhotos;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getCategories(photoId) {
      axios
        .get(this.apiUrl + "/c/category/" + photoId)
        .then((res) => {
          const categories = res.data;
          console.table(categories);
          if (categories == null) return;
          this.categories = categories;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getPhotos();
    this.photos.forEach((photo) => {
    this.getCategories(photo.id);
    });
  },
};
</script>

<style lang="scss" scoped>
.card-img-top {
  height: 400px;
}
</style>
