 function mostrar(input, img)
        {
			console.log(input);
            var objeto=document.getElementById(img);
			if(img === "contenido1"){
				if(input.value === "Mostrar contenido 1"){
				 objeto.style.display="block";
				input.value="Ocultar contenido 1";
				 
				}else{
				objeto.style.display="none";
				input.value="Mostrar contenido 1";
				}
			}else{
				if(input.value === "Mostrar contenido 2"){
				 objeto.style.display="block";
				input.value="Ocultar contenido 2";
				 
				}else{
				objeto.style.display="none";
				input.value="Mostrar contenido 2";
				}
			}

 	}