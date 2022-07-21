<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>${title }</title>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
<script>
	
	$(document).ready(function() {
		
		getAllStudents();
		$('#saveStudent').show();
		$('#updateStudent').hide();
		$('#idfield').hide();
		$('#placedStudent').hide();
		
		/* $('#gettime').click(function(){
			$.ajax({
				url : "studentReport/time",
				success : function(data) {
					$('#printtime').html(data);
				}
			});
		});
		 */
		$('#saveStudent').click(function() {
			$.ajax({
				type: "post",
				url: "insertStudent",
				data: {
					name : $("#name").val(),
					email : $("#email").val(),
					college : $("#college").val(),
					course : $("#course").val()
				},
				success : function(result) {
					alert(result);
					getAllStudents();
					$('#studentForm')[0].reset();
				},
				error : function(err) {
					alert("error is "+err)
				}
			});
		});
	});
		 
		 var data = "";
			function getAllStudents() {
				$
						.ajax({
							type : "GET",
							url : "getAllStudents",
							success : function(response) {
								 data = response
								 
								 $('.tr').remove();
								for (i = 0; i < data.length; i++) {
									$("#studentTable")
											.append(
													'<tr class="tr"> <td>'
															+ data[i].id
															+ '</td> <td>'
															+ data[i].name
															+ '</td> <td>'
															+ data[i].email
															+ '</td> <td>'
															+ data[i].college
															+ '</td> <td>'
															+ data[i].course
															+ '</td> <td><input type="button" class="btn btn-warning" onclick="editStudent('
															+ data[i].id
															+ ')"  value="Edit"></input></td> <td> <input type="button" class="btn btn-danger" onclick="deleteStudent('
															+ data[i].id
															+ ');" value="Delete"></input></td> </tr>');
								}
							},
							error : function(err) {
								alert("error is" + err)
							}
						});
			}
			
			function editStudent(id) {
				$.ajax({
					type : "GET",
					url : "getStudentById/"+id,
					dataType : 'json',
					success : function(response) {
						$("#id").val(response.id),$("#name").val(response.name), $("#email").val(response.email),$("#college").val(response.college), $("#course").val(response.course)
						
						$("#saveStudent").hide();
						$("#updateStudent").show();
						$('#idfield').show();
						$('#placedStudent').show();
					},
					err : function(err) {
						alert("error is "+err);
					}
				});
			}
			
			function placeStudentBtn() {
				$.ajax({
					type : "POST",
					url : "placeStudent",
					data : {
						id : $("#id").val(),
						name : $("#name").val(),
						email : $("#email").val(),
						college : $("#college").val(),
						course : $("#course").val()
					},
					success : function(result) {
						alert(result);
						getAllStudents();
						$('#saveStudent').show();
						$('#updateStudent').hide();
						$('#idfield').hide();
						$('#studentForm')[0].reset()
					},
					error : function(err) {
						alert("error is" + err)
					}
				});
			}
			
			function updateStudentbtn() {
				$.ajax({
					type : "POST",
					url : "updateStudent",
					data : {
						id : $("#id").val(),
						name : $("#name").val(),
						email : $("#email").val(),
						college : $("#college").val(),
						course : $("#course").val()
					},
					success : function(result) {
						alert(result);
						getAllStudents();
						$('#saveStudent').show();
						$('#updateStudent').hide();
						$('#idfield').hide();
						$('#studentForm')[0].reset()
					},
					error : function(err) {
						alert("error is" + err)
					}
				});
			}
			
			function deleteStudent(id) {
				$.ajax({
					type : "DELETE",
					url : "deleteStudent/" + id,
					success : function(result) {
						alert(result);
						getAllStudents();
					},
					error : function(err) {
						alert("error is" + err)
					}
				});
			}
	
</script>
</head>
<body>
	<h1 style="text-align:center; margin:30px 0px;">Student CRUD Using AJAX </h1>
	
	<!-- <button id="gettime">Get Time</button>
	<h2 id="printtime"></h2> -->

	<div class="container mt-3">
		<form id="studentForm" name="studentform">
			<div class="row">
				<div class="col-6">
					<h3>Add Student Form</h3>


					<div class="row" id="idfield">
						<div class="col">
							<div class="form-group">
								<label for="id">ID</label> <input type="text" readonly="readonly"
									class="form-control" id="id" name="id">
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="name">Name</label> <input type="text"
									class="form-control" id="name" name="name"
									placeholder="Enter Name">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="email">Email</label> <input type="email"
									class="form-control" id="email" name="email"
									placeholder="Enter Email">
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="college">College</label> <input type="text"
									class="form-control" id="college" name="college"
									placeholder="Enter College">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="name">Course</label> <input type="text"
									class="form-control" id="course" name="course"
									placeholder="Enter Course">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<button type="button" id="saveStudent" class="btn btn-primary">Submit</button>
							<button type="button" id="updateStudent"
								onclick="updateStudentbtn()" class="btn btn-primary">Update</button>
							<button type="button" id="placedStudent"
								onclick="placeStudentBtn()" class="btn btn-primary">Placed</button>
						</div>
					</div>
				</div>
				<div class="col-6">

					<h3>Student Records</h3>
					
					 <br>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
								<th scope="col">Email</th>
								<th scope="col">College</th>
								<th scope="col">Course</th>
								<th scope="col">Edit</th>
								<th scope="col">Delete</th>
							</tr>
						</thead>
						<tbody id="studentTable">

						</tbody>
					</table>


				</div>


			</div>
		</form>
	</div>

</body>
</html>