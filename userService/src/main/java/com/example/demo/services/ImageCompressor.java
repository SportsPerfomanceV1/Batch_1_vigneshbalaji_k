package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.IOException;

@Service
public class ImageCompressor {
	 public byte[] compress(byte[] data) throws IOException, java.io.IOException {
	        if (data == null || data.length == 0) {
	            return data; // Return the original data if it's null or empty
	        }

	        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
	            gzipOutputStream.write(data);
	            gzipOutputStream.finish(); // Finish the compression
	            return byteArrayOutputStream.toByteArray(); // Return the compressed data
	        }
	    }
	 public byte[] decompress(byte[] compressedData) throws IOException, java.io.IOException {
	        if (compressedData == null || compressedData.length == 0) {
	            return compressedData; // Return the original data if it's null or empty
	        }

	        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
	             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len;
	            while ((len = gzipInputStream.read(buffer)) != -1) {
	                byteArrayOutputStream.write(buffer, 0, len);
	            }
	            return byteArrayOutputStream.toByteArray(); // Return the decompressed data
	        }
	    }
}
