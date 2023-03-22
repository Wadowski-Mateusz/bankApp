// Pobranie elementów HTML
const navbar = document.querySelector('.navbar');

let prevScrollpos = window.pageYOffset;
// Obsługa zdarzenia przewijania strony
window.onscroll = function() {
  let currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    navbar.classList.remove('hidden');
  } else {
    navbar.classList.add('hidden');
  }
  prevScrollpos = currentScrollPos;
}