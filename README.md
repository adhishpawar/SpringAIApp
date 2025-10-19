
# 🧠 Spring AI Codebase – Integrating AI with Spring Boot

## 📘 Overview
This repository demonstrates the integration of **Spring Boot** with **Spring AI** to build intelligent and context-aware applications.  
It combines capabilities like **OpenAI models**, **Ollama models**, **image generation**, **embeddings**, and **RAG (Retrieval-Augmented Generation)** using **Vector Stores**.

---

## ⚙️ Key Features

### 🧩 1. OpenAI and Ollama Integration
- Uses `OpenAiChatModel` and `OllamaChatModel` for intelligent chatbot conversations.
- Demonstrates message prompting, response handling, and model metadata access.

### 🧠 2. Embeddings and Vector Store
- `EmbeddingModel` generates embeddings for text data.
- `SimpleVectorStore` stores and retrieves similar documents efficiently.
- The project showcases text chunking using `TokenTextSplitter` and data initialization with `TextReader`.

### 🗂️ 3. RAG (Retrieval-Augmented Generation)
- Combines vector-based similarity search with a chatbot model.
- Uses `QuestionAnswerAdvisor` for contextual answering based on document similarity.

### 🖼️ 4. AI-Powered Image Generation and Description
- Integrates with OpenAI’s image model to:
  - Generate images from text prompts.
  - Describe uploaded images using multimodal AI capabilities.

### 🎬 5. Movie Recommendation System
- Demonstrates prompt templating using `PromptTemplate` and `Prompt`.
- Generates movie recommendations with detailed responses such as plot, cast, and IMDB rating.

---

## 🧱 Project Structure

```
com.adhish.SpringAICode
├── AppConfig.java              # Configures VectorStore with Embedding Model
├── DataInitializer.java        # Loads and embeds data from text file
├── ImageGenController.java     # Image generation and description endpoints
├── Movie.java                  # Simple POJO for movie details
├── OllamaController.java       # Chat with Ollama local AI model
├── OpenAiController.java       # Main API for OpenAI chat and embeddings
└── SpringAiCodeApplication.java # Main Spring Boot application entry point
```

---

## 🚀 APIs and Endpoints

| Endpoint | Method | Description |
|-----------|---------|-------------|
| `/api/{message}` | POST | Get AI-generated response from OpenAI |
| `/api/recommend` | POST | Movie recommendation based on type, year, and language |
| `/api/embedding` | POST | Generate embedding vector for text |
| `/api/similarity` | POST | Compute cosine similarity between two texts |
| `/api/product` | POST | Retrieve most similar documents using VectorStore |
| `/api/ask` | POST | Ask questions using RAG (Retrieval-Augmented Generation) |
| `/image/{query}` | GET | Generate image from text prompt |
| `/image/describe` | POST | Describe uploaded image with textual prompt |

---

## 🧰 Technologies Used

- **Spring Boot 3.x**
- **Spring AI**
- **OpenAI API**
- **Ollama Model Integration**
- **Vector Store & Embedding Model**
- **RAG (Retrieval-Augmented Generation)**
- **Jakarta Annotations**
- **REST APIs**

---

## 🧩 How It Works

1. **Initialization**
   - The application loads `product_details.txt` using `TextReader`.
   - Text is split into tokens and converted into embeddings using `EmbeddingModel`.
   - The embeddings are stored in `VectorStore`.

2. **Querying**
   - When a user sends a query, `VectorStore` performs a **similarity search**.
   - Top-k similar documents are fetched and passed as context to the **AI model** for RAG-based answers.

3. **Image Tasks**
   - Users can generate or describe images using the **OpenAI Image Model**.

---

## 🧩 Example Usage

### Movie Recommendation Example
```bash
POST /api/recommend?type=sci-fi&year=2023&lang=English
```
**Response:**
```
1. Movie: Dune: Part Two
2. Plot: Continuation of Paul Atreides' journey for vengeance and destiny.
3. Cast: Timothée Chalamet, Zendaya, Rebecca Ferguson
4. Length: 2h 46min
5. IMDB Rating: 8.9
```

---

## 🧪 Future Work

✅ Add persistent storage for VectorStore (e.g., PostgreSQL, Redis).  
✅ Integrate multimodal models for image + text RAG.  
✅ Build a dashboard UI using React for visualization of embeddings and chat logs.  
✅ Extend support for custom fine-tuned models (domain-specific AI).  
✅ Add user authentication and role-based access to AI APIs.  

---

## 🧑‍💻 Author

**Adhish Pawar**  
Final Year Computer Engineering Student | AI & GPU Enthusiast  
President, CSI Club – PES Modern College of Engineering  
📫 [LinkedIn](https://www.linkedin.com/in/adhishpawar)

---

## 📜 License
This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

**✨ Empowering Spring Boot with AI – Built using Spring AI and OpenAI APIs ✨**
