<template>
  <div class="pt-5">
    <h2 class="mt-4">Edit News</h2>
    <br>

        <form method="post" v-on:submit.prevent = "editNews()" >
          <div class="form-group">
            <label for="title">Title</label>
            <input style="margin-top: 10px;" v-model="title" v-text="title" type="text" class="form-control" id="title" placeholder="Enter title">
          </div>
          <div class="form-group">
            <label for="content" style="margin-top: 10px;">Content</label>
            <textarea style="margin-top: 10px;" cols="40" rows="5" v-model="content" v-text="content" type="text" class="form-control" id="content" placeholder="Enter content">
     </textarea>
          </div>
          <br>
          <div class = "row"  style="text-align: center">
            <div class="col form-group">
              <b-form-select v-model = "selectedCategory"  class="m-3">
                <b-form-select-option  v-for="category in categories" :key="category.name" :value= "category" >{{category.name}}</b-form-select-option>
              </b-form-select>
            </div>
          </div>
          <br>  <br>
          <br>
          <div style="text-align: center">
            <button  type="submit" class="btn btn-primary">Publish News</button>
          </div>
        </form>
        <br>
        <br>
        <form method="post" v-on:submit.prevent = "addTag()" >
          <div class="form-group">
            <label for="tag">Tag</label>
            <input style="margin-top: 10px;" v-model="tag" v-text="tag" type="text" class="form-control" id="tag" placeholder="Enter tag">
          </div>
          <br>
          <br>
          <div style="text-align: center">
            <button  type="submit" class="btn btn-primary">Add Tag</button>
          </div>
        </form>
      </div>
</template>
<script>

export default {
  name: "UpdateNews",
  data() {
    return {
      title: null,
      content: null,
      categories: [],
      selectedCategory: null,
      tag: null
    }
  },
  mounted() {
    this.$axios.get(`news/${this.$route.params.id}`).then((response) => {
      const news = response.data;
      this.title = news.title;
      this.content = news.content;
      this.$axios.get('categories?page=1').then((response) => {
        this.categories = response.data;
        for (const category of this.categories) {
          if (category.categoryId == news.categoryId)
            this.selectedCategory = category;
        }
      });
    });
  },
  methods: {
    editNews() {
      this.$axios.put(`news/${this.$route.params.id}`, {
        "title": this.title,
        "content": this.content,
        "categoryId":this.selectedCategory.categoryId
      }).then(() => {
        this.$router.push(`/news`);
      });
    },
    addTag() {
      this.$axios.post(`tags/${this.$route.params.id}`, {
        "keyword": this.tag,
      }).then(() => {
        this.$router.push(`/news`);
      });
    },
  }
}
</script>

<style scoped>

</style>
