package com.example.vehicletrackingapp;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.List;

public class Pojo {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pojo withMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Pojo withResult(List<Result> result) {
        this.result = result;
        return this;
    }
    public class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("time")
        @Expose
        private String time;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Result withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Result withTime(String time) {
            this.time = time;
            return this;
        }

    }
}

