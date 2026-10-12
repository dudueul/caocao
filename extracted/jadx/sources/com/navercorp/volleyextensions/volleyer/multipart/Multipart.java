package com.navercorp.volleyextensions.volleyer.multipart;

import com.navercorp.volleyextensions.volleyer.util.StringUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Multipart implements Collection<Part>, Writable {
    private static final int DEFAULT_BOUNDARY_LENGTH = 30;
    private String boundary;
    private List<Part> parts;

    public Multipart() {
        this(StringUtils.generateRandom(30));
    }

    public Multipart(String str) {
        this.parts = new ArrayList();
        assertBoundary(str);
        this.boundary = str;
    }

    private static void assertBoundary(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Boundary must not be empty.");
        }
    }

    private void writeBoundaryEnd(OutputStream outputStream) throws IOException {
        outputStream.write(("----" + this.boundary + "--\n").getBytes());
    }

    private void writeBoundaryStart(OutputStream outputStream) throws IOException {
        outputStream.write(("----" + this.boundary + "\n").getBytes());
    }

    private void writeParts(OutputStream outputStream) throws IOException {
        for (Part part : this.parts) {
            writeBoundaryStart(outputStream);
            part.write(outputStream);
        }
        writeBoundaryEnd(outputStream);
    }

    @Override // java.util.Collection
    public boolean add(Part part) {
        if (part == null) {
            return false;
        }
        return this.parts.add(part);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends Part> collection) {
        if (collection == null) {
            return false;
        }
        boolean zAdd = false;
        Iterator<? extends Part> it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection
    public void clear() {
        this.parts.clear();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.parts.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.parts.containsAll(collection);
    }

    public String getBoundary() {
        return this.boundary;
    }

    public String getContentType() {
        return "multipart/form-data; boundary=--" + this.boundary;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.parts.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<Part> iterator() {
        return this.parts.iterator();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        return this.parts.remove(obj);
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.parts.removeAll(collection);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.parts.retainAll(collection);
    }

    @Override // java.util.Collection
    public int size() {
        return this.parts.size();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.parts.toArray();
    }

    @Override // java.util.Collection
    public <Part> Part[] toArray(Part[] partArr) {
        return (Part[]) this.parts.toArray(partArr);
    }

    @Override // com.navercorp.volleyextensions.volleyer.multipart.Writable
    public void write(OutputStream outputStream) throws IOException {
        if (this.parts.size() == 0) {
            return;
        }
        writeParts(outputStream);
    }
}
