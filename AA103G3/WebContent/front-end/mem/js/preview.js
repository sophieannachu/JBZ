var file;
var fileReader;
var photo;
var preview;
var hide_img;
function doFirst(){
	
	photo = document.getElementById('photo');
	photo.onchange = fileChange;
	preview = document.getElementsByClassName('preview')[0];
	hide_img= document.getElementsByClassName('hide_img')[0];
	
}
function fileChange(e){

	file=e.srcElement.files[0];

	fileReader = new FileReader();
	fileReader.readAsDataURL(file);
	
	fileReader.onload = function(){
		preview.src=fileReader.result;
		hide_img.src=fileReader.result;
	};
}
window.addEventListener('load',doFirst,false);