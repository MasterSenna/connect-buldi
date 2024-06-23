document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.star');
    const ratingValue = document.getElementById('ratingValue');
    const submitButton = document.getElementById('submitRating');
    const successMessage = document.getElementById('successMessage');
    let currentRating = 0;

    stars.forEach(star => {
        star.addEventListener('click', () => {
            currentRating = star.getAttribute('data-value');
            updateRating(currentRating);
        });

        star.addEventListener('mouseover', () => {
            updateRating(star.getAttribute('data-value'));
        });

        star.addEventListener('mouseout', () => {
            updateRating(currentRating);
        });
    });

    submitButton.addEventListener('click', () => {
        showSuccessMessage();
        // Aqui você pode adicionar a lógica para enviar a avaliação para o servidor, por exemplo:
        // fetch('/submit-rating', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({ rating: currentRating }),
        // })
        // .then(response => response.json())
        // .then(data => {
        //     console.log('Success:', data);
        // })
        // .catch((error) => {
        //     console.error('Error:', error);
        // });
    });

    function updateRating(rating) {
        stars.forEach(star => {
            if (star.getAttribute('data-value') <= rating) {
                star.classList.add('active');
            } else {
                star.classList.remove('active');
            }
        });
        ratingValue.textContent = rating;
    }

    function showSuccessMessage() {
        successMessage.classList.remove('hidden');
        setTimeout(() => {
            successMessage.classList.add('hidden');
            window.location.href = '/mentorado/perfilMentorado'; // Redireciona para a nova página após 3 segundos
        }, 3000); // Oculta a mensagem e redireciona após 3 segundos
    }
});
