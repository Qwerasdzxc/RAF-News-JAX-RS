<template>
  <div>

    <h2>
      Title: {{ news.title }}
    </h2>

    <h6 style="margin-top: 18px">
      Category: {{category != null ? category.name : ""}}
    </h6>

    <div v-if="tags">
    <h6 style="margin-top: 12px">Tags:</h6>
    <h6 v-for="tag in tags" :key = "tag.tagId" @click="toTag(tag.tagId)">
      {{tag.keyword}}
    </h6>
    </div>
    <h5  style="margin-top: 12px">
      Author: {{ author != null ? author.name : "" }}
    </h5>

    <h6  style="margin-top: 12px">
      Created: {{new Date(news.created) }}
    </h6>
    <br>
    <h4>Description:</h4>
    <p>
      {{ news.content }} <br><br>
    </p>
    <br>
    <h2>Add comment:</h2>
    <div class="form-group">
      Date: {{currentDateTime()}}
    </div>
    <br>
    <form method="post" v-on:submit.prevent = "postComment()" >
      <div class="form-group">
        <label for="name">Name</label>
        <input required v-model="authorName" type="text" class="form-control" id="name" placeholder="Enter your name">
      </div>
      <br>

      <div class="form-group">
        <label for="content">Comment</label>
        <input required v-model="content" type="text" class="form-control" id="content" placeholder="Comment">
      </div>
      <br>
      <button type="submit" class="btn btn-primary mt-2">Publish</button>
    </form>

    <br> <br>
    <h3 style="margin-top: 12px">Comments:</h3>
    <h6 v-for="comment in comments" :key = "comment.id">

     Author:
      {{comment.authorName}}

      <br>
      {{new Date(comment.created)}}
      <br>
      <b-card style="margin-top: 10px">
      <h4 style="margin-top: 15px;margin-left: 20px">{{comment.content}}</h4>
      </b-card>
    </h6>


  </div>
</template>

<script>


export default {
  name: "NewsDetails",
  props: {
    news: {
      type: Object,
      required: true,
    }
  },
  data() {
    return {
      tags: [],
      comments: [],
      authorName: null,
      content: null,
      author: null,
      created: null,
      category: null
    }
  },
  mounted() {
    this.$axios.get(`tags/tags-by-news?newsId=${this.$route.params.id}`).then((response) => {
        this.tags = response.data;
    })
    this.$axios.get(`comments/${this.$route.params.id}`).then((response) => {
        this.comments = response.data;
    });
    this.$axios.get(`news/${this.$route.params.id}`).then((response) => {
        this.$axios.get(`categories/${response.data.categoryId}`).then((response) => {
          this.category = response.data;
        });
        this.$axios.get(`users/${response.data.authorId}`).then((response) => {
          this.author = response.data;
        })
    });
    
  },
  created() {

  },
  methods: {
    postComment(){
      this.$axios.post(`comments`, {
        "authorName": this.authorName,
        "content": this.content,
        "newsId": this.news.newsId
      }).then(() => {
        window.location.reload();
      });
    },
    currentDateTime() {
      const current = new Date();
      const date = current.getFullYear()+'-'+(current.getMonth()+1)+'-'+current.getDate();
      const time = current.getHours() + ":" + current.getMinutes() + ":" + current.getSeconds();
      const dateTime = date +' '+ time;

      return dateTime;
    },
    toTag(id) {
      this.$router.push(`/news/tag/${id}`);
    }
  }
}
</script>

<style scoped>

</style>
