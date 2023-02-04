function addFooter(){
    document.getElementById("footer").innerHTML = `
<footer style="background-color: #0c0d0d;">
  <div style="height: 250px;" class="container">
    <div class="container-fluid col-lg-8">
      <div class="justify-content-center">
        <ul class="list-unstyled mb-0 row" style="padding-top: 80px; padding-bottom: 70px; text-align:center;">
          <li class="mb-2 col-3"><a href="/home" class="text-muted">Home</a></li>
          <li class="mb-2 col-3"><a href="/login" class="text-muted">Login</a></li>
          <li class="mb-2 col-3"><a href="/registration" class="text-muted">Registration</a></li>
          <li class="mb-2 col-3"><a href="/credits" class="text-muted">Credits</a></li>
        </ul>
      </div>
    </div>


    <div class="container-fluid">
      <div class="row">
        <h5 class="col-12" style="text-align: center; color: white">© 2023 Copyright: V.O.V</h5>
      </div>
    </div>
  </div>
</footer>
`;
}