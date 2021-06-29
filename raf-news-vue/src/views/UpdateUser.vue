<template>
  <div class="pt-5">
    <form method="post" v-on:submit.prevent = "editUser()" >
      <div class="form-group">
        <label for="email">Email</label>
        <input style="margin-top: 10px;" required  v-model="email" v-text="email" type="email" class="form-control" id="email" >
      </div>
      <br>
      <div class="form-group">
        <label for="name" style="margin-top: 10px;">Name</label>
        <input style="margin-top: 10px;" required  v-model="name" v-text="name" type="name" class="form-control" id="name" placeholder="Enter name">

      </div>
      <br>
      <div class="form-group">
        <label for="surname" style="margin-top: 10px;">Surname</label>
        <input style="margin-top: 10px;" required  v-model="surname" v-text="surname" type="name" class="form-control" id="surname" placeholder="Enter surname">
      </div>
      <br>
      <br>
      <div class="form-group">
        <label for="isAdmin" style="margin-top: 10px;">Is Administrator</label>
        <input style="margin-top: 10px;" required  v-model="isAdmin" type="number" class="form-control" id="isAdmin" placeholder="Is Administrator">
      </div>
      <br>

      <button type="submit" class="btn btn-primary mt-2">Update user</button>
    </form>
  </div>
</template>

<script>


export default {
  name: "UpdateUser",
  data() {
    return {
      email: null,
      name: null,
      surname: null,
      isAdmin: null,
    }
  },
  mounted() {
    this.$axios.get(`users/${this.$route.params.id}`).then((response) => {
      const user = response.data;
      this.email = user.email;
      this.name = user.name;
      this.surname = user.surname;
      this.isAdmin = user.admin ? "1" : "0";
    });
  },
  methods: {
    editUser(){
      this.$axios.put(`users/${this.$route.params.id}`, {
        "email": this.email,
        "name": this.name,
        "surname": this.surname,
        "admin": this.isAdmin === 'true' || this.isAdmin === '1',

      }).then(() => {
        this.$router.push(`/users`);
      })
    },

  }
}
</script>

<style scoped>

</style>
