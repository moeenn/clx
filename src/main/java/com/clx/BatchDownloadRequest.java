package com.clx;

import java.util.List;

public record BatchDownloadRequest(List<String> urls) {
}
