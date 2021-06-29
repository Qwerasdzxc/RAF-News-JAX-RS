<template>
  <div class="pt-5">
    <h2 class="mt-4">Edit Category</h2>
    <br>

        <form method="post" v-on:submit.prevent = "editCategory()" >
          <div class="form-group">
            <label for="name">Name</label>
            <input style="margin-top: 10px;" required  v-model="name" v-text="name" type="text" class="form-control" id="name" placeholder="Enter name">
          </div>
          <div class="form-group">
            <label for="description" style="margin-top: 10px;">Description</label>
            <input style="margin-top: 10px;" required  v-model="description" v-text="description" type="text" class="form-control" id="description" placeholder="Enter description">

          </div>
          <br>

          <button type="submit" class="btn btn-primary mt-2">Update Category</button>
        </form>
      </div>
</template>
<script>

export default {
  name: "UpdateCategory",
  data() {
    return {
      name: null,
      description: null,
    }
  },
  created() {
    this.$axios.get(`categories/${this.$route.params.id}`).then((response) => {
      const category = response.data;
      this.name = category.name;
      this.description = category.description;
    });
  },
  methods: {
    editCategory() {
      this.$axios.put(`categories/${this.$route.params.id}`, {
        "name": this.name,
        "description": this.description
      }).then(() => {
        this.$router.push(`/category`);
      });
    },
  }
}
</script>

<style scoped>

</style>
