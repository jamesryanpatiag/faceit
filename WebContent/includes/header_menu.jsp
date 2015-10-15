<div class="navbar navbar-blue navbar-static-top">  
      <div class="navbar-header">
      	<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle</span>
          <span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
        </button>
        <a href="/" class="navbar-brand logo">FaceIT</a>
    	</div>
    	<nav class="collapse navbar-collapse" role="navigation">
      <form class="navbar-form navbar-left">
          <div class="input-group input-group-sm" style="max-width:360px;">
          <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
          <div class="input-group-btn">
            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
          </div>
        </div>
    </form>
    <ul class="nav navbar-nav">
      <li>
        <a href="Newsfeed"><i class="glyphicon glyphicon-home"></i> Home</a>
      </li>
      <li>
        <a href="Profile?profile=${userid}" role="button"><i class="glyphicon glyphicon-user"></i> ${userfullname}</a>
      </li>
      <!--  <li>
        <a href="#"><span class="badge">badge</span></a>
      </li>-->
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-menu-down"></i></a>
        <ul class="dropdown-menu">
          <li><a href="">More</a></li>
          <li><a href="">More</a></li>
          <li><a href="">More</a></li>
          <li><a href="">More</a></li>
          <li><a href="">More</a></li>
        </ul>
      </li>
    </ul>
  	</nav>
</div>