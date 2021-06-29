<template>
  <div class="pt-5">
    <form method="post" v-on:submit.prevent = "postUser()" >
      <div class="form-group">
        <label for="email">Email</label>
        <input style="margin-top: 10px;" required  v-model="email" type="email" class="form-control" id="email" placeholder="Enter email">
      </div>
      <br>
      <div class="form-group">
        <label for="name" style="margin-top: 10px;">Name</label>
        <input style="margin-top: 10px;" required  v-model="name" type="name" class="form-control" id="name" placeholder="Enter name">

      </div>
      <br>
      <div class="form-group">
        <label for="surname" style="margin-top: 10px;">Surname</label>
        <input style="margin-top: 10px;" required  v-model="surname" type="lastname" class="form-control" id="surname" placeholder="Enter surname">
      </div>
      <br>
      <div class="form-group">
        <label for="password" style="margin-top: 10px;">Password</label>
        <input style="margin-top: 10px;" required  v-model="password" type="password" class="form-control" id="password" placeholder="Enter password">
      </div>
      <div class="form-group">
        <label for="confirmPassword" style="margin-top: 10px;">Confirm Password</label>
        <input style="margin-top: 10px;" required  v-model="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="Repeat password">
      </div>
      <br>
      <div class="form-group">
        <label for="isAdmin" style="margin-top: 10px;">Is Administrator</label>
        <input style="margin-top: 10px;" required  v-model="isAdmin" type="number" class="form-control" id="isAdmin" placeholder="Is Administrator">
      </div>
      <br>
      <br>

      <button type="submit" class="btn btn-primary mt-2">Add User</button>
    </form>

    <h1 class="mt-4">Users</h1>
    <div class="row" style="display:inline;">
      <div class="col-4 mx-auto" >
        <table class=" table text-center" style="width: 650px;margin-left: -150px;">
          <thead>
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Is Admin</th>
            <th scope="col">Is Active</th>
            <th scope="col">Edit</th>
            <th scope="col">Change Status</th>
          </tr>
          </thead>
          <tbody >
          <tr v-for="user in users" :key="user.email" >
            <b-card style="margin-top: 10px">
              <td scope="row"> {{ user.email }}</td>
            </b-card>
            <td scope="row"> {{ user.name }}</td>
            <td scope="row"> {{ user.surname }}</td>
            <td scope="row"> {{ user.admin }}</td>
            <td scope="row"> {{ user.active }}</td>
            <td scope="row">
              <b-button @click="editUser(user.userId)" size="sm">Edit</b-button>
            </td>
            <td scope="row" v-if="user.admin == false">
              <b-button  @click="changeStatus(user.userId)" size="sm">Change</b-button>
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
  name: "CreateUser",
  data() {
    return {
      date: '',
      email: null,
      name: null,
      surname: null,
      password: null,
      confirmPassword: null,
      isAdmin: null,
      isActive: null,
      users: [],
    }
  },
  mounted() {
    this.$axios.get('users?page=1').then((response) => {
      this.users = response.data;
    });
  },
  methods: {
    editUser(id) {
      this.$router.push(`/user/update/${id}`);
    },
    postUser() {
    if (this.password != this.confirmPassword)
        return;

      this.$axios.post('users', {
        "email": this.email,
        "name": this.name,
        "surname": this.surname,
        "password": this.password,
        "admin": this.isAdmin === 'true' || this.isAdmin === '1',

      }).then(() => {
        window.location.reload()
      })
    },
    changeStatus(id){
      this.$axios.put(`users/change-status/${id}`).then(() => {
        window.location.reload()
      })
    },

  }
}
</script>

<style scoped>

</style>
