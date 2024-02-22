package org.unlogged.springwebfluxdemo.model;

import java.util.*;

public class GenericTypeWrapper<T> {

    private List<T> listObject;
    private Collection<T> collectionObject;
    private Map<Integer, T> mapObject;
    private HashMap<Integer, T> hashMapObject;
    private TreeMap<Integer, T> treeMapObject;
    private Set<T> setObject;
    private HashSet<T> hashSetObj;
    private TreeSet<T> treeSet;
    private LinkedList<T> linkedlistObj;
    private Optional<T> optionalObj;

    public GenericTypeWrapper(T value) {
        this.listObject = Arrays.asList(value);
        this.collectionObject = listObject;
        hashMapObject = new HashMap<>();
        hashMapObject.put(1, value);
        mapObject = hashMapObject;
        treeMapObject = new TreeMap<>();
        treeMapObject.put(1, value);
        hashSetObj = new HashSet<>();
        hashSetObj.add(value);
        setObject = hashSetObj;
        treeSet = new TreeSet<>();
        treeSet.add(value);
        linkedlistObj = new LinkedList<>();
        linkedlistObj.add(value);
        optionalObj = Optional.of(value);
    }

    public List<T> getListObject() {
        return listObject;
    }

    public void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }

    public Collection<T> getCollectionObject() {
        return collectionObject;
    }

    public void setCollectionObject(Collection<T> collectionObject) {
        this.collectionObject = collectionObject;
    }

    public Map<Integer, T> getMapObject() {
        return mapObject;
    }

    public void setMapObject(Map<Integer, T> mapObject) {
        this.mapObject = mapObject;
    }

    public HashMap<Integer, T> getHashMapObject() {
        return hashMapObject;
    }

    public void setHashMapObject(HashMap<Integer, T> hashMapObject) {
        this.hashMapObject = hashMapObject;
    }

    public TreeMap<Integer, T> getTreeMapObject() {
        return treeMapObject;
    }

    public void setTreeMapObject(TreeMap<Integer, T> treeMapObject) {
        this.treeMapObject = treeMapObject;
    }

    public Set<T> getSetObject() {
        return setObject;
    }

    public void setSetObject(Set<T> setObject) {
        this.setObject = setObject;
    }

    public HashSet<T> getHashSetObj() {
        return hashSetObj;
    }

    public void setHashSetObj(HashSet<T> hashSetObj) {
        this.hashSetObj = hashSetObj;
    }

    public TreeSet<T> getTreeSet() {
        return treeSet;
    }

    public void setTreeSet(TreeSet<T> treeSet) {
        this.treeSet = treeSet;
    }

    public LinkedList<T> getLinkedlistObj() {
        return linkedlistObj;
    }

    public void setLinkedlistObj(LinkedList<T> linkedlistObj) {
        this.linkedlistObj = linkedlistObj;
    }

    public Optional<T> getOptionalObj() {
        return optionalObj;
    }

    public void setOptionalObj(Optional<T> optionalObj) {
        this.optionalObj = optionalObj;
    }

    @Override
    public String toString() {
        return "GenericTypeWrapper{" +
                "listObject=" + listObject +
                ", collectionObject=" + collectionObject +
                ", mapObject=" + mapObject +
                ", hashMapObject=" + hashMapObject +
                ", treeMapObject=" + treeMapObject +
                ", setObject=" + setObject +
                ", hashSetObj=" + hashSetObj +
                ", treeSet=" + treeSet +
                ", linkedlistObj=" + linkedlistObj +
                ", optionalObj=" + optionalObj +
                '}';
    }
}
