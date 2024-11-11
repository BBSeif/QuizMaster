// const questions = [
//     {
//         question: " Which is largest animal in the world?",
//         answers: [
//             {text: "Shark", correct: false},
//             {text: "Blue whale", correct: false},
//             {text: "Elephant", correct: true},
//             {text: "Giraffe", correct: false},
//             {text: "Zebra", correct: false},
//         ]
//     },
//     {
//         question: " Which is largest hh animal in the world?",
//         answers: [
//             {text: "Sharhk", correct: false},
//             {text: "Blue whale", correct: false},
//             {text: "Elephant", correct: true},
//             {text: "Giraffe", correct: false},
//             {text: "Zebra", correct: false},
//         ]
//     }
// ];



// const apiUrl = 'http://localhost:1111/api/quiz';
// // 'https://your-api-url/questions';

// async function getQuestionsFromApi() {
//     try {
//         const response = await fetch(apiUrl);
//         const data = await response.json();
//         return data;
//     } catch (error) {
//         console.error('Error fetching questions:', error);
//     }
// }

///
const apiUrl = 'http://localhost:1111/api/quiz';

async function getQuestionsFromApi() {
    try {
        const response = await fetch(apiUrl);

        if (!response.ok) {
            // Handle non-successful responses (e.g., 404 Not Found, 500 Internal Server Error)
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching questions:', error.message);
        // You may choose to rethrow the error here or handle it in a different way
    }
}

// Example usage:
getQuestionsFromApi()
    .then(data => console.log('Questions:', data))
    .catch(error => console.error('Failed to fetch questions:', error));



let questions = [];

///

const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");
const nextButton = document.getElementById("next-btn");

let currentQuestionIndex = 0;
let score = 0;


async function startQuiz() {
    // Fetch questions from the API
    questions = await getQuestionsFromApi();

    currentQuestionIndex = 0;
    score = 0;
    nextButton.innerHTML = 'Келесі сұрақ';
    showQuestion();
}



// function startQuiz(){
//     currentQuestionIndex = 0;
//     score = 0;
//     nextButton.innerHTML = "Келесі сұрақ";
//     showQuestion();
// }

function showQuestion(){
    resetState();
    let currentQuestion = questions[currentQuestionIndex];
    let questionNo = currentQuestionIndex + 1;
    questionElement.innerHTML = questionNo + ". " + currentQuestion.question;

    currentQuestion.answers.forEach(answer => {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btn");
        answerButtons.appendChild(button);
        if(answer.correct){
            button.dataset.correct = answer.correct;
        }
        button.addEventListener("click", selectAnsware);
    });
}

function resetState(){
    nextButton.style.display = "none";
    while(answerButtons.firstChild){
        answerButtons.removeChild(answerButtons.firstChild)
    }
}

function selectAnsware(e){
    const selectBtn = e.target;
    const isCorrect = selectBtn.dataset.correct === "true";
    if(isCorrect){
        selectBtn.classList.add("correct");
        score++;
    } else {
        selectBtn.classList.add("incorrect");
    }
    Array.from(answerButtons.children).forEach(button => {
        if(button.dataset.correct === "true"){
            button.classList.add("correct");
        }
        button.disabled = true;
    });
    nextButton.style.display = "block";
}

function showScore(){
    resetState();
    questionElement.innerHTML = questions.length + " сұрақтың " + score + " дұрыс " + " !";
    nextButton.innerHTML = "Қайта тапсыру";
    nextButton.style.display = "block";
}

function handleNextButton(){
    currentQuestionIndex++;
    if(currentQuestionIndex < questions.length){
        showQuestion();
    } else {
        showScore();
    }
}

nextButton.addEventListener("click", ()=>{
    if(currentQuestionIndex < questions.length){
        handleNextButton();
    } else {
        startQuiz();
    }
});

startQuiz();
