<template>
  <div class="pt-5">
    <form method="post" @submit.prevent="search()" >
      <div class="form-group">
        <label for="query">Query</label>
        <input style="margin-top: 10px;" v-model="query" type="text" class="form-control" id="query" placeholder="Enter query">
      </div>
      <br>
      <br>
      <div style="text-align: center">
      <button  type="submit" class="btn btn-primary">Search News</button>
      </div>
   </form>
    <br>  <br>
    <table class=" table text-center" style="width: 650px;margin-left: 300px;">

      <thead>
      <tr>
        <th scope="col">Title</th>
        <th scope="col">Created</th>
        <th scope="col">Content</th>
      </tr>
      </thead>

      <tbody >

      <tr v-for="news in newsList" :key="news.newsId" >

        <b-card style="margin-top: 10px">
          <td scope="row" @click="find(news.newsId)"> {{ news.title }}</td>
        </b-card>

        <td>{{new Date(news.created).toISOString() }}</td>
        <td>{{ news.content | shortText }}</td>
      </tr>

      </tbody>

    </table>

  </div>
</template>

<script>


export default {
  name: "SearchNews",
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
      query: null,
      newsList: []
    }
  },
  methods: {
    search() {
      this.$axios.get(`news/search?page=1&query=${this.query}`).then((response) => {
        this.newsList = response.data;
      });
    },
    find(id) {
      this.$router.push(`news/${id}`);
    },
  }
}
</script>

<style scoped>

</style>
