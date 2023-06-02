let occupationsByGender = {
    MALE: ["Mechanic", "Electrician", "Plumber", "Glazier", "Taxi driver"],
    FEMALE: ["Nurse", "Choreographer", "Receptionist", "Travel agent", "Social worker"]
};

const select = document.getElementById("occupations");

// Add event listener for change event
select.addEventListener("change", function() {
    // Get all the selected options
    const selectedOptions = Array.from(this.selectedOptions);

    // Iterate over each option and update the style
    this.querySelectorAll("option").forEach(option => {
        if (selectedOptions.includes(option)) {
            option.style.color = "green"; // Set selected option color to green
        } else {
            option.style.color = "red"; // Set unselected option color to red
        }
    });
});

function fillOccupations() {
    let gender = document.getElementById("gender").value;
    let occupationSelect = document.getElementById("occupations");
    occupationSelect.innerHTML = "";

    let occupations = occupationsByGender[gender];
    if (occupations) {
        for (let i = 0; i < occupations.length; i++) {
            let option = document.createElement("option");
            option.value = occupations[i];
            option.text = occupations[i];
            occupationSelect.appendChild(option);
        }
    }
}

document.getElementById("gender").addEventListener("change", fillOccupations);

fillOccupations();