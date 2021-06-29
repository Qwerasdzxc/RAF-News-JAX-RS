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

            <td>{{new Date(news.created)}}</td>
            <td>{{ news.content | shortText }}</td>

          </tr>

          </tbody>

        </table>
      </div>
      <div class="col-6" >
        <NewsDetails v-if="selectedNews" :news="selectedNews"></NewsDetails>
      </div>
    </div>
  </div>
</template>

<script>
import NewsDetails from "../components/NewsDetails";

export default {
  components: {NewsDetails},
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
      newsList: []
    }
  },
  methods: {
    find(id) {
        this.$router.push(`/news/${id}`);
    }
  },
  mounted() {
    this.$axios.get(`tags/news-by-tag?tagId=${this.$route.params.id}`).then((response) => {
      this.newsList = response.data;
    });
  }
}
</script>
