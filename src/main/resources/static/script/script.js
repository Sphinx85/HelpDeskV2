let toggleA = document.querySelector(".lex_card_button");
console.log(toggleA);
let detailCard = document.querySelector(".lex_card_text");
console.log(detailCard);
let a = 0;

function openCloseDetails() {
  if (a == 0) {
    console.log("a=1");
    detailCard.style.display = "block";
    return (a = 1);
  } else {
    detailCard.style.display = "none";
    console.log("a=0");
    return (a = 0);
  }
  // console.log(a);
}

toggleA.addEventListener("click", openCloseDetails);
