<template>
  <div>

    <h2>
      Title: {{ news.title }}
    </h2>
    <h7>
      Visits: {{ news.viewCount }}
    </h7>

    <h6 style="margin-top: 18px">
      Category: {{news.categoryId}}
    </h6>

    <h6 style="margin-top: 12px">Tags:</h6>
    <h6 v-for="tag in tags" :key = "tag.tagId">
      {{tag.keyword}}
    </h6>

    <h5  style="margin-top: 12px">
      Author: {{ news.authorId }}
    </h5>

    <h6  style="margin-top: 12px">
      Created: {{new Date(news.created)}}
    </h6>
    <br>
    <h4>Description:</h4>
    <p>
      {{ news.content }} <br><br>
    </p>
  </div>
</template>

<script>

export default {
  name: "NewsVisitDetails",
  props: {
    news: {
      type: Object,
      required: true,
    }
  },
  data() {
    return {
      tags: []
    }
  },
  mounted() {
    this.$axios.get(`tags/tags-by-news?newsId=${this.$route.params.id}`).then((response) => {
        this.tags = response.data;
    })
  }
}
</script>

<style scoped>

</style>
