<html>
<head>


<title>Spring ES CRUD Example</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>
<script language="JavaScript" type="text/JavaScript">
		
		function getUserlist(){
			$(".user-dropdown").html("");
			$.ajax({
				url : '/api/getNameList',
				type : 'GET',
				success : function(data) {
					for (var s in data){
						$("<option value='" + data[s] + "'>" + data[s] +"</option>").appendTo('.user-dropdown');
						
					}
				}
			});
			
		}
		
		$( document ).ready(function() {
			
			$('#getAllBtn').click(function(){
				
				$.ajax({
					url : '/api/getAll',
					type : 'GET',
					success : function(data) {
						alert(JSON.stringify(data));
					},error : function(rsp){
						console.log(rsp)
						alert("error:"+rsp.responseText);
					}
				});
		
			})
			
			
			$('#getUserBtn').click(function(){
				
				$.ajax({
					url : '/api/getUser?name='+$('.user-dropdown').val(),
					type : 'GET',
					success : function(data) {
						alert(JSON.stringify(data));
					},error : function(rsp){
						console.log(rsp)
						alert("error:"+rsp.responseText);
					}
				});
		
			})
			
			
			$('#create1Btn').click(function(){
				
				$.ajax({
					url : '/api/create1',
					type : 'GET',
					success : function(data) {
						alert(JSON.stringify(data));
						getUserlist();
					}
				});
		
			})
			
			
			$('#create2Btn').click(function(){
				
				$.ajax({
					url : '/api/create2',
					type : 'GET',
					success : function(data) {
						alert(JSON.stringify(data));
						getUserlist();
					}
				});
		
			})
			
			
			
			$('#deleteBtn').click(function(){
				
				$.ajax({
					url : '/api/delete?id='+$('#delete_id').val(),
					type : 'DELETE',
					success : function(data) {
						alert(JSON.stringify(data));
						
						
						getUserlist();
					}
				});
		
			})
			
			
			$('#updateBtn').click(function(){
				
				$.ajax({
					url : '/api/update?id='+$('#update_id').val()+'&msg='+$('#update_msg').val(),
					type : 'PUT',
					success : function(data) {
						alert(JSON.stringify(data));
					}
				});
		
			})
			
			getUserlist();
			
		
		}); //end of $(document).ready
		
		
	</script>

</head>
<body>


	<div class="container" style="padding-top: 50px;">

		<h1>Elasticsearch Java API</h1>
		<p class="lead">Spring CRUD Example</p>

		<br />
		<div class="row">
			<div class="col-md-6">
				<h2>GET ALL</h2>
				<p>
					<a id="getAllBtn" class="btn btn-primary" href="#" role="button">GET
						All &raquo;</a>
				</p>
			</div>
			<div class="col-md-6">
				<h2>GET by name</h2>
				<p>

					<select class="user-dropdown" aria-labelledby="dropdownMenuButton">

					</select>

				</p>
				<p>
					<a id="getUserBtn" class="btn btn-primary" href="#" role="button">GET
						by Name &raquo;</a>
				</p>
			</div>

		</div>

		<br />
		<h3></h3>
		<div class="row">
			<div class="col-md-3">
				<h2>CREATE1</h2>
				<pre>
{
 "id": "1"
 "user": "testUser",
 "postDate": "2017-08-21",
 "message": "trying out Elasticsearch"
} 
			</pre>
				<p>
					<a class="btn btn-primary" id="create1Btn" href="#" role="button">CREATE
						&raquo;</a>
				</p>
			</div>


			<div class="col-md-3">
				<h2>CREATE2</h2>
				<pre>
{
 "id": "2"
 "user": "testUser",
 "postDate": "2017-08-21",
 "message": "trying out Elasticsearch"
} 
			</pre>
				<p>
					<a class="btn btn-primary" id="create2Btn" href="#" role="button">CREATE
						&raquo;</a>
				</p>
			</div>

			<div class="col-md-3">
				<h2>UPDATE</h2>
				<p>
					<input id="update_id" class="form-control" placeholder="id">
				</p>

				<p>
					<input id="update_msg" class="form-control" placeholder="message">
				</p>
				<p>
					<a id="updateBtn" class="btn btn-primary" href="#" role="button">UPDATE
						&raquo;</a>
				</p>
			</div>

			<div class="col-md-3">
				<h2>DELETE</h2>
				<p>
					<input id="delete_id" class="form-control" placeholder="id">
				</p>
				<p>
					<a id="deleteBtn" class="btn btn-primary" href="#" role="button">DELETE
						&raquo;</a>
				</p>
			</div>
		</div>
		<hr/>
		 <footer>
        <p>&copy; Tsung Wu</p>
      </footer>

	</div> <!-- end of container -->

</body>

</html>
