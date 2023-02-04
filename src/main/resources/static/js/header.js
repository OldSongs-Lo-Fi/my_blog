function addHeader(){
    document.getElementById("header").innerHTML = `
<header style="background-color: #0c0d0d" class="p-3 text-white">
  <div class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-between">

      <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <li><a href="/home" style="font-size: 20px" class="nav-link px-2 text-white">Home</a></li>
        <li><a href="/posts" style="font-size: 20px" class="nav-link px-2 text-white">Features</a></li>
        <li><a href="/credits" style="font-size: 20px" class="nav-link px-2 text-white">Credits</a></li>
      </ul>

      <div class="text-end">
        <a href="/login"><button type="button" style="font-size: 20px" class="btn btn-outline-light me-2">Login</button></a>
        <a href="/registration"><button type="button" style="font-size: 20px" class="btn btn-warning">Sign-up</button></a>
      </div>
    </div>
  </div>
</header>

`;
}