<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row " id="margin-body">
	<div class="col-sm-12">
                    
                    <div class="col-lg-4 ">
                        <a href="showEditProfile"><i class="fa fa-fw fa-book fa-3x setting-icon" >
                        </i><span style="color: black;">Edit Profile</span> </a>
                   </div>
                    <sec:authorize access="hasRole('ROLE_USER')">
					<div class="col-sm-4">
                        <a href="updateAllPoint2"><i class="fa fa-fw fa-book fa-3x setting-icon">
                        </i><span style="color: black;">View Current Point</span> </a>
                   </div>
                   </sec:authorize>
            </div>
</div>