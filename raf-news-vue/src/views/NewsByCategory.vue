<template>
  <div class="news" style="text-align: center">
    <h1 class="mt-4">News</h1>

    <div class="row" style="display:inline;">
      <div class="col-4 mx-auto" >
        <table class=" table text-center" style="width: 650px;margin-left: -150px;">

          <thead>
          <tr>
            <th scope="col">Title</th>
            <th scope="col">Created</th>
            <th scope="col">Content</th>
          </tr>
          </thead>

          <tbody >

          <tr v-for="news in newsList" :key="news.newsId" @click="find(news.newsId)">

            <b-card style="margin-top: 10px">
              <td scope="row"> {{ news.title }}</td>
            </b-card>

            <td>{{new Date(news.created) }}</td>
            <td>{{ news.content | shortText }}</td>

          </tr>

          </tbody>

        </table>
      </div>
      <div class="col-6" >
        <NewsCom v-if="selectedNews" :news="selectedNews"></NewsCom>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "NewsByCategory",

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
      selectedNews: null,
      newsList: [],
      componentKey: 0,

    }
  },
  methods: {
    find(id) {
      this.$router.push(`/news/${id}`);

    },
    forceRerender() {
      this.componentKey += 1;
    }
  },
  mounted() {
    this.$axios.get(`news/news-for-category?page=1&categoryId=${this.$route.params.id}`).then((response) => {
      this.newsList = response.data;

    });
  },
}
</script>

<style scoped>

</style>
