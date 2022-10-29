/*

let response = await fetch("http://localhost:8187/api/users");

if (response.ok) { // если HTTP-статус в диапазоне 200-299
                   // получаем тело ответа (см. про этот метод ниже)
    let json = await response.json();
    alert(json);
} else {
    alert("Ошибка HTTP: " + response.status);
}*/
fetch("http://localhost:8187/api/users").then(
    res=>{res.json().then(
        data=>{
            console.log(data)
        }
    )
    }
)