FROM openjdk:17-jdk-slim

# Buat direktori /opt untuk aplikasi
RUN mkdir -p /opt

# Buat user baru
RUN useradd -ms /bin/bash appuser

# Salin file JAR ke direktori /opt
COPY target/demo.jar /opt/app.jar

# Ubah kepemilikan direktori /opt ke user baru
RUN chown -R appuser:appuser /opt

# Set direktori kerja ke /opt
WORKDIR /opt

# Jalankan aplikasi sebagai user baru
USER appuser

# Expose port 8080
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
