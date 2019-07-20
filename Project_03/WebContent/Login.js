var table = document.getElementById("table");

function addRows(size, user) {
	var i;
	var row;
	var j;
	var cell1;
	var cell2;
	var cell3;
	for (i = 0; i < size; i++) {
		row = table.insertRow(i);

		cell1 = row.insertCell(0);
		cell2 = row.insertCell(1);
		cell3 = row.insertCell(2);

		cell1.innerHTML = user.getUsername();
		cell1.innerHTML = user.getEmail();
		cell2.innerHTML = user.getAge;
	}

}
