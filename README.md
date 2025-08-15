# Fresh Farm Organics

**FreshFarm Organics** is a **full-stack Java Spring Boot web application** for an online organic farm store.  
The platform brings the farm directly to customers’ doors, combining modern **e-commerce functionality** with a commitment to **sustainability** and **quality**.

---

## 📖 Project Summary

FreshFarm Organics allows customers to browse organic produce, add items to their cart, and securely check out with a Stripe test integration. Customers can manage their profile, update delivery addresses, and contact support — all within a clean, responsive interface.

---

## 🚀 Features


- **Home page** - View our story and mission.

- **Browse products** - Categories: fruits, vegetables, dairy, cheese, yoghurt, and boxes.

- **Search products** - Quickly find items across all categories, by keyword.

- **Shopping cart** - Add, update, remove, and clear items.

- **Check out** - Order summary, update address, and **Stripe test payment**.

- **Customer Profile** - Manage personal details, update delivery address, and order history.
  
- **Contact Form** - Reach out to customer support directly from the app.
  
- **FAQ Section** - Get quick answers to common questions.
  
---

## 🛠 Tech Stack


- **Backend**: Java 21, Spring Boot (MVC), Spring Data JPA
- **Frontend**: Thymeleaf, HTML5, CSS3
- **Database**: PostgreSQL
- **Authentication**: OAuth 2.0 / OIDC (Auth0)
- **Payments (Simulated)**: Stripe
- **Design**: Figma
- **Project Management**: Trello

---

## Prerequisites


To successfully run the project, ensure you have the following installed:

- **Java** – version 21 or higher
- **Maven** – version 3.8.x or higher
- **PostgreSQL** – recommended minimum version 12.x (PostgreSQL)
- **IDE** – IntelliJ IDEA (recommended) or any Java-compatible IDE

---

## ⚡ Quick Start ⚡


#### 1. Clone the repository:

   ```bash
git clone https://github.com/Magdaleeena/freshFarm_Organics.git
cd freshFarm_Organics
```

#### 2. Create PostgreSQL databases on your local machine:
  ```bash
createdb fresh_farm_organics_development
createdb fresh_farm_organics_test
  ```

#### 3. Seed your database with customers and products:
  ```bash
mvn spring-boot:run -Dspring-boot.run.profiles=seed
```

#### 4. Build the App and start the server:
```bash
mvn spring-boot:run
```

#### 5. Run the tests:
```bash
mvn test
# or a single test:
mvn -Dtest=HeaderTests test
```

---

## Demo Credentials (Test Account Only):

These credentials are for demonstration and testing purposes only. They do not belong to real users.

- **Email address**: hancocklewis@example.net
- **Password**: Hello123!

### Stripe Test Payment Details:
- **Card Number**: 4242 4242 4242 4242
- **Expiry date**: Any future date
- **Security number**: Any three digits (except 999)
- **Email**: Any
- **Cardholder Name**: Any name
- **Postcode**: Any

#### Additional Test Cards for Error Scenarios:
- **4000 0000 0000 0002** → Generic decline error  
- **4000 0000 0000 9995** → Insufficient funds error  
- **4000 0000 0000 0069** → Expired card error (use any past date)  
- **4000 0000 0000 0127** → Incorrect CVC error (use wrong CVC)

---

## 📡 API Endpoints

#### Home

- `GET /` → Our Story

#### Customer

- `GET /login` → Auth0 login/signup

- `GET /logout` → Logout 
  
- `GET /profile` → View profile (auth required)

- `POST /profile` → Update profile (auth required)
  
#### Products

- `GET /fruits` → List of all fruits  
 
- `GET /vegetables` → List of all vegetables
  
- `GET /milk` → List of all milk
  
- `GET /cheese` → List of all cheese
  
- `GET /yoghurt` → List of all yoghurt
  
- `GET /boxes` → List of all mixed-boxes

#### Search

- `GET /search?q={keyword}` → Search across all products by keyword

#### Cart

- `GET /cart` → View basket

- `POST /cart/add` → Add item

- `POST /cart/update` → Update quantity

- `POST /cart/remove` → Remove item

- `POST /cart/clear` → Empty basket

#### Checkout

- `GET /checkout` → View your order summary

- `POST /checkout/create-session` → Start payment 

- `GET /success` → Payment success

#### Contact us

- `GET /contact_us` → View & submit form

- `POST /contact_us` → Submit query

#### FAQ

- `GET /faq` → Frequently asked questions

---


## Security
- Auth0 **authentication**: This allows secure login and registration without storing raw passwords in our system.

All protected endpoints require a valid Auth0-issued token, ensuring that only authenticated users can access sensitive data such as profile details, order history, and checkout.

---

## Notes
- This project is fully responsive and mobile-friendly.
- Stripe is in test mode — no real payments are processed.
