$(document).ready(function() {
    $('#calculate').click(function() {
        var weight = parseFloat($('#weight').val());
        var height = parseFloat($('#height').val()) / 100;
        
        if (weight > 0 && height > 0) {
            var bmi = weight / (height * height);
            var bmiStatus = '';
            if (bmi < 18.5) {
                bmiStatus = 'Underweight';
                $('#result').css('color', 'blue');
            } else if (bmi >= 18.5 && bmi < 25) {
                bmiStatus = 'Normal weight';
                $('#result').css('color', 'green');
            } else if (bmi >= 25 && bmi < 30) {
                bmiStatus = 'Overweight';
                $('#result').css('color', 'orange');
            } else {
                bmiStatus = 'Obese';
                $('#result').css('color', 'red');
            }
            $('#result').html('Your BMI is: ' + bmi.toFixed(2) + ' (' + bmiStatus + ')');
        } else {
            $('#result').html('Please enter valid data.').css('color', 'red');
        }
    });
});
