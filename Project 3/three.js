//Scene and Camera Initialization
var scene = new THREE.Scene();
scene.background = new THREE.Color(0x808080);
var camera = new THREE.PerspectiveCamera(100, window.innerWidth/window.innerHeight);

//Renderer Initialization
var renderer = new THREE.WebGLRenderer({antialias: true});
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild( renderer.domElement );

//Radio Button Controls
var shape1 = true;
var shape2 = true;
var shape3 = true;
var shape4 = true;
var shape5 = true;
var shape6 = true;

var light1 = true;
var light2 = true;

var check1 = document.querySelector("input[name=shape1]");
var check2 = document.querySelector("input[name=shape2]");
var check3 = document.querySelector("input[name=shape3]");
var check4 = document.querySelector("input[name=shape4]");
var check5 = document.querySelector("input[name=shape5]");
var check6 = document.querySelector("input[name=shape6]");
var check7 = document.querySelector("input[name=light1]");
var check8 = document.querySelector("input[name=light2]");

check1.addEventListener( 'change', function() {
	if(this.checked) {
		shape1 = true;
	}
	else {
		shape1 = false;
	}
});
check2.addEventListener( 'change', function() {
	if(this.checked) {
		shape2 = true;
	}
	else {
		shape2 = false;
	}
});
check3.addEventListener( 'change', function() {
	if(this.checked) {
		shape3 = true;
	}
	else {
		shape3 = false;
	}
});
check4.addEventListener( 'change', function() {
	if(this.checked) {
		shape4 = true;
	}
	else {
		shape4 = false;
	}
});
check5.addEventListener( 'change', function() {
	if(this.checked) {
		shape5 = true;
	}
	else {
		shape5 = false;
	}
});
check6.addEventListener( 'change', function() {
	if(this.checked) {
		shape6 = true;
	}
	else {
		shape6 = false;
	}
});
check7.addEventListener( 'change', function() {
	if(this.checked) {
		light1 = true;
	}
	else {
		light1 = false;
	}
});
check8.addEventListener( 'change', function() {
	if(this.checked) {
		light2 = true;
	}
	else {
		light2 = false;
	}
});

//Ambient Light
var ambient = new THREE.AmbientLight(0x404040);
scene.add(ambient);

//Spot Light
var light = new THREE.SpotLight(0xffffff);
light.position.set(100, 100, 100);
light.castShadow = true;
scene.add(light);

//Shape 1 = Cube
var geometry1 = new THREE.BoxGeometry(1, 1, 1);
var material = new THREE.MeshLambertMaterial({color: 0xffff00}); //Lambert material responds to light sources
var cube = new THREE.Mesh(geometry1, material);
scene.add(cube);
cube.position.z = -5;
cube.rotation.x = 10;
cube.rotation.y = 5;

//Shape2 = Dodecahedron
var geometry2 = new THREE.DodecahedronGeometry(1, 0);
var dodec = cube.clone();
dodec.geometry = geometry2;
scene.add(dodec);
dodec.position.x += 3;
dodec.position.z = -5;

//Shape 3 = Cone
var geometry3 = new THREE.ConeGeometry(1, 1, 20);
var cone = cube.clone();
cone.geometry = geometry3;
scene.add(cone);
cone.position.x -= 3;
cone.position.z = -5;

//Shape 4 = Octahedron
var geometry4 = new THREE.OctahedronGeometry(1, 0);
var octa = cube.clone();
octa.geometry = geometry4;
scene.add(octa);
octa.position.x -= 6;
octa.position.z = -5;
octa.rotation.x = 5;
octa.rotation.y = 5;

//Shape 5 = Cylinder
var geometry5 = new THREE.CylinderGeometry(1, 1, 1, 20);
var cylinder = cube.clone();
cylinder.geometry = geometry5;
scene.add(cylinder);
cylinder.position.x += 6;
cylinder.position.z = -5;

//Shape 6 = Torus
var geometry6 = new THREE.TorusGeometry(1, 0.4, 8, 6);
var torus = cube.clone();
torus.geometry = geometry6;
scene.add(torus);
torus.position.y += 2.5;
torus.position.z = -5;

var animate = function() {
	if(shape1) {
		cube.rotation.x += 0.01;
	}
	
	if(shape2) {
		dodec.rotation.x += 0.01;
	}
	
	if(shape3) {
		cone.rotation.x += 0.01;
	}
	
	if(shape4) {
		octa.rotation.x += 0.01;
		octa.rotation.y += 0.01;
	}
	
	if(shape5) {
		cylinder.rotation.x += 0.01;
	}
	
	if(shape6) {
		torus.rotation.y += 0.01;
	}
	
	if(!light1) {
		ambient.visible = false;
	}
	else {
		ambient.visible = true;
	}
		
	
	if(!light2) {
		light.visible = false;
	}
	else {
		light.visible = true;
	}
	
	renderer.render(scene, camera);
	requestAnimationFrame(animate);
}
animate();