const showSettingItems = (t) => {
  document.getElementsByName("dropdown-item").forEach((e) => {
    if (e.classList.contains("display-none")) {
      e.classList.remove("display-none");
    } else {
      e.classList.add("display-none");
    }
  });
}