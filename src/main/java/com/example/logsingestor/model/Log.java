package com.example.logsingestor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;
import java.util.Objects;


@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("level")
    private String level;

    @JsonProperty("message")
    private String message;

    @JsonProperty("resourceId")
    private String resourceId;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private String timestamp;

    @JsonProperty("traceId")
    private String traceId;

    @JsonProperty("spanId")
    private String spanId;

    @JsonProperty("commit")
    private String commit;

    @ElementCollection
    @CollectionTable(name = "log_metadata", joinColumns = @JoinColumn(name = "log_id"))
    @MapKeyColumn(name = "metadata_key")
    @Column(name = "metadata_value")
    private Map<String, String> metadata;


    public Log() {
    }

    public Log(Long id, String level, String message, String resourceId, String timestamp,
               String traceId, String spanId, String commit, Map<String, String> metadata) {
        this.id = id;
        this.level = level;
        this.message = message;
        this.resourceId = resourceId;
        this.timestamp = timestamp;
        this.traceId = traceId;
        this.spanId = spanId;
        this.commit = commit;
        this.metadata = metadata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (!Objects.equals(id, log.id)) return false;
        if (!Objects.equals(level, log.level)) return false;
        if (!Objects.equals(message, log.message)) return false;
        if (!Objects.equals(resourceId, log.resourceId)) return false;
        if (!Objects.equals(timestamp, log.timestamp)) return false;
        if (!Objects.equals(traceId, log.traceId)) return false;
        if (!Objects.equals(spanId, log.spanId)) return false;
        if (!Objects.equals(commit, log.commit)) return false;
        return Objects.equals(metadata, log.metadata);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (traceId != null ? traceId.hashCode() : 0);
        result = 31 * result + (spanId != null ? spanId.hashCode() : 0);
        result = 31 * result + (commit != null ? commit.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", message='" + message + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", traceId='" + traceId + '\'' +
                ", spanId='" + spanId + '\'' +
                ", commit='" + commit + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
