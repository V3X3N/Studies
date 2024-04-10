function resizeImage() {
    const fileInput = document.getElementById('fileInput');
    const resolutionSelect = document.getElementById('resolution');
    const output = document.getElementById('output');

    const file = fileInput.files[0];
    const selectedResolution = resolutionSelect.value.split('x');
    const width = parseInt(selectedResolution[0]);
    const height = parseInt(selectedResolution[1]);

    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = new Image();
            img.onload = function () {
                const canvas = document.createElement('canvas');
                const ctx = canvas.getContext('2d');

                canvas.width = width;
                canvas.height = height;

                ctx.drawImage(img, 0, 0, width, height);

                const newImageUrl = canvas.toDataURL('image/jpeg');

                const link = document.createElement('a');
                link.href = newImageUrl;
                link.download = 'resized_image.jpg';
                link.innerHTML = '<img src="' + newImageUrl + '" alt="Resized Image">';

                output.innerHTML = '';
                output.appendChild(link);
            }
            img.src = e.target.result;
        }
        reader.readAsDataURL(file);
    } else {
        output.innerHTML = 'Proszę wybrać plik zdjęcia.';
    }
}
