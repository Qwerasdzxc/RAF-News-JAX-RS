<template>
  <div class="pt-5">
    <form method="post" v-on:submit.prevent = "postCategory()" >
      <div class="form-group">
        <label for="name">Name</label>
        <input style="margin-top: 10px;" required  v-model="name" type="text" class="form-control" id="name" placeholder="Enter name">
      </div>
      <div class="form-group">
        <label for="description" style="margin-top: 10px;">Description</label>
        <input style="margin-top: 10px;" required  v-model="description" type="text" class="form-control" id="description" placeholder="Enter description">

      </div>
      <br>

      <button type="submit" class="btn btn-primary mt-2">Create Category</button>
    </form>

    <h1 class="mt-4">Categories</h1>
    <div class="row" style="display:inline;">
      <div class="col-4 mx-auto" >
        <table class=" table text-center" style="width: 650px;margin-left: -150px;">
          <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Delete</th>
            <th scope="col">Edit</th>
          </tr>
          </thead>
          <tbody >
          <tr v-for="category in categories" :key="category.name">
            <b-card style="margin-top: 10px">
              <td scope="row" @click="findCategory(category.categoryId)"> {{ category.name }}</td>
            </b-card>
            <td scope="row"> {{ category.description }}</td>
            <td scope="row">
              <b-button v-if="categories.length > 1" @click="deleteCategory(category.categoryId)" size="sm">Delete</b-button>
            </td>
            <td scope="row">
              <b-button @click="editCategory(category.categoryId)">Edit</b-button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "CreateCategory",
  data() {
    return {
      date: '',
      name: null,
      description: null,
      categories: [],
    }
  },
  mounted() {
    this.$axios.get('categories?page=1').then((response) => {
      this.categories = response.data;
    });
  },
  methods: {
    editCategory(id) {
      this.$router.push(`/category/update/${id}`)
    },
    deleteCategory(id) {
      this.$axios.delete(`categories/${id}`).then(() => {});
      window.location.reload()
    },
    postCategory(){
      this.$axios.post('categories', {
        "name": this.name,
        "description": this.description
      }).then(() => {
        window.location.reload()
      })
    },
    findCategory(id) {
      this.$router.push(`/news/category/${id}`);
    }
  }
}
</script>

<style scoped>

</style>
