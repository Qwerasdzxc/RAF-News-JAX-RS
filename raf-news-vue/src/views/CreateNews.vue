<template>
  <div class="pt-5">
    <form method="post" @submit.prevent="postNews()" >
      <div class="form-group">
        <label for="title">Title</label>
        <input style="margin-top: 10px;" v-model="title" type="text" class="form-control" id="title" placeholder="Enter title">
      </div>
      <div class="form-group">
        <label for="content" style="margin-top: 10px;">Content</label>
        <textarea style="margin-top: 10px;" cols="40" rows="5" v-model="content" type="text" class="form-control" id="content" placeholder="Enter content">
     </textarea>
      </div>
      <br>
      <div class = "row"  style="text-align: center">
      <div class="col form-group">
        <b-form-select v-model = "selectedCategory" class="m-3">
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
    <br>  <br>
    <table class=" table text-center" style="width: 650px;margin-left: 300px;">

      <thead>
      <tr>
        <!--            <th scope="col">ID</th>-->
        <th scope="col">Title</th>
        <th scope="col">Created</th>
        <th scope="col">Content</th>
        <th scope="col">Delete</th>
        <th scope="col">Edit</th>
      </tr>
      </thead>

      <tbody >

      <tr v-for="news in newsList" :key="news.newsId" >

        <b-card style="margin-top: 10px">
          <td scope="row" @click="find(news.newsId)"> {{ news.title }}</td>
        </b-card>

        <td>{{new Date(news.created).toISOString() }}</td>
        <td>{{ news.content | shortText }}</td>
        <td scope="row">
          <b-button v-if="newsList.length > 1" @click="deleteNews(news.newsId)" size="sm">Delete</b-button>
        </td>
        <td scope="row">
          <b-button @click="editNews(news.newsId)" size="sm">Edit</b-button>
        </td>

      </tr>

      </tbody>

    </table>

  </div>
</template>

<script>


export default {
  name: "CreateNews",
  filters: {
    shortText(value) {
      if (value.length < 30) {
        return value;
      }
      return value.slice(0, 30) + '...'
    }
  },
  data() {
    return {
      date: '',
      selectedCategory: null,
      title: null,
      content: null,
      category: [],
      categories: [],
      selectKorisnici: [],
      users: [],
      newsList: []
    }
  },
  mounted() {
    this.$axios.get('categories?page=1').then((response) => {
      this.categories = response.data;
    });
    this.$axios.get('users?page=1').then((response) => {
      this.users = response.data;
    });
    this.$axios.get('news/all?page=1').then((response) => {
      this.newsList = response.data;
    });
  },
  methods: {
    deleteNews(id) {
      this.$axios.delete(`news?newsId=${id}`).then(() => {
        
      });
      window.location.reload()
    },
    find(id) {
      this.$router.push(`news/${id}`);

    },
    editNews(id) {
      this.$router.push(`/news/update/${id}`)
    },
    postNews(){
      this.$axios.post(`news`, {
        "title": this.title,
        "content": this.content,
        "authorId": 1,
        "categoryId":1

      }).then(() => {
        window.location.reload();
      });
    },
  }
}
</script>

<style scoped>

</style>
