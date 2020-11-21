/**
 * 
 */

jQuery(document).ready(function($) {
	$("#nav-placeholder").load("http://localhost:8080/navbar");
});

const img = document.getElementById('labImg');

// Load the model.
cocoSsd.load().then(model => {
    // detect objects in the image.
    model.detect(img).then(predictions => {
      console.log('Predictions: ', predictions);
    });
  });
