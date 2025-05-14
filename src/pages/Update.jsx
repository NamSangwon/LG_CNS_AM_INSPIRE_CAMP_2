const Update = () => {
  return (
    <>
      <div class="container mt-5">
        <div class="row">
          <form method="post" th:action="@{/board/update/} + ${board.id}">
            <div class="mb-3">
              <label for="title">Title:</label>
              <input type="text" class="form-control" id="title" name="title" value="원본 값"/>
            </div>
            <div class="mb-3">
              <label for="content">Content:</label>
              <textarea class="form-control" rows="5" name="content" id="content" value="원본 값"></textarea>
            </div>
            <div class="d-grid gap-2">
              <button class="btn btn-primary" id="complete">수정완료</button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default Update;