<template>
  <div>
    <div class="container">
      <div class="row">
        <form class="d-flex justify-content-center mt-5" role="search">
          <input
            class="form-control me-2 w-25"
            type="search"
            placeholder="Find your photo..."
            aria-label="Search"
            v-model="searchQuery"
            @keyup="getSearchPhotos()"
          />
          <button
            class="btn btn-primary"
            type="submit"
            @click="getSearchPhotos()"
          >
            Search
          </button>
        </form>

        <div class="col d-flex flex-wrap mt-4">
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

export default {
  name: "Home",
  data() {
    return {
      apiUrl: "http://localhost:8080/api/1",
      photos: [],
      searchQuery: "",
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

    getIndexFromPhoto(photoId) {
      return this.photos.findIndex((photo) => photo.id === photoId);
    },

    getCategoryPhoto(photoId) {
      axios
        .get(this.apiUrl + "/category/" + photoId)
        .then((res) => {
          const categoriesWphoto = res.data;
          console.table(categoriesWphoto);
          if (categoriesWphoto == null) return;

          const index = this.getIndexFromPhoto(photoId);
          this.photos[index].categories = categoriesWphoto;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getSearchPhotos() {
      if (this.searchQuery == "") return this.getPhotos();
      axios
        .get(this.apiUrl + "/photos/search/" + this.searchQuery)
        .then((result) => {
          this.photos = "";
          this.photos = result.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getPhotos();
  },
};
</script>

<style lang="scss" scoped>
.card-img-top {
  height: 400px;
}
</style>
