package com.clx.dto;

import java.util.List;

public record BatchDownloadRequest(List<String> urls) {
}
