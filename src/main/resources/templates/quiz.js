// Sample quiz data (questions and correct answers)
const quizData = /* Insert your server response here */;

// Get HTML elements
const questionContainer = document.getElementById('question-container');
const optionsContainer = document.getElementById('options-container');
const resultContainer = document.getElementById('result-container');
const submitButton = document.getElementById('submit-button');

// Initialize quiz
let currentQuestion = 0;
loadQuestion();

// Function to load a question
function loadQuestion() {
    const currentQuizData = quizData[currentQuestion];

    // Check if all questions are answered
    if (!currentQuizData) {
        endQuiz();
        return;
    }

    questionContainer.innerText = currentQuizData.question;

    // Clear options container
    optionsContainer.innerHTML = '';

    // Create options
    currentQuizData.options.forEach((option, index) => {
        const optionElement = document.createElement('div');
        optionElement.classList.add('option');
        optionElement.innerText = option;
        optionElement.addEventListener('click', () => selectOption(index));
        optionsContainer.appendChild(optionElement);
    });

    // Enable the submit button
    submitButton.disabled = false;
}

// Function to handle option selection
function selectOption(selectedIndex) {
    const currentQuizData = quizData[currentQuestion];
    const selectedOption = currentQuizData.options[selectedIndex];

    // Add styling to indicate selection
    document.querySelectorAll('.option').forEach((option, index) => {
        option.classList.remove('selected');
        if (index === selectedIndex) {
            option.classList.add('selected');
        }
    });

    // Store selected answer
    currentQuizData.selectedAnswer = selectedOption;
}

// Function to submit the answer for the current question
function submitAnswer() {
    const currentQuizData = quizData[currentQuestion];

    if (currentQuizData.selectedAnswer === undefined) {
        alert('Please select an answer before submitting.');
        return;
    }

    // Check if the answer is correct
    const isCorrect = currentQuizData.selectedAnswer === currentQuizData.correctAnswer;

    // Display result
    resultContainer.innerText = isCorrect ? 'Correct!' : 'Incorrect. The correct answer is: ' + currentQuizData.correctAnswer;

    // Disable the submit button for this question
    submitButton.disabled = true;

    // Move to the next question or end the quiz
    setTimeout(() => {
        resultContainer.innerText = '';
        currentQuestion++;
        loadQuestion();
    }, 2000);
}

// Function to end the quiz
function endQuiz() {
    questionContainer.innerText = 'Quiz completed!';
    optionsContainer.innerHTML = '';
    submitButton.style.display = 'none';
}
