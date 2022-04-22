let toggleA = document.querySelector(".lex_card_button");
console.log(toggleA);
let detailCard = document.querySelector(".lex_card_text");
console.log(detailCard);
// let wrapperBlockCard = document.querySelector("left_block_down");
// console.log(wrapperBlockCard);
let a = 0;

function openCloseDetails() {
  if (a == 0) {
    detailCard.style.display = "block";
    console.log(a);
    return (a = 1);
  } else {
    detailCard.style.display = "none";
    console.log(a);
    return (a = 0);
  }
}

// document.addEventListener("click", () => {
//   var target = event.target;

//   // if (target.tagName != "BUTTON")
//   return console.log(toggleA);

//   // target.style.background = "black";
// });

toggleA.addEventListener("click", openCloseDetails);

// function openCloseDetails({ target }) {
//   if (target.classList.contains("lex_card_button")) {
//     let lex_card_text = target
//       .closest(".lex_card")
//       .querySelector(".lex_card_text");
//     lex_card_text.hidden = !lex_card_text.hidden;

//     // let lex_card_text = target
//     //   .closest(".lex_card")
//     //   .querySelector(".lex_card_text");
//     // lex_card_text.style.display = "block";
//   }
// }

// document
//   .querySelector(".left_block_down")
//   .addEventListener("click", openCloseDetails);
