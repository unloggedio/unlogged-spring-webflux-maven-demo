package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.*;

@RestController
public class SequencedCollectionsController {

    @RequestMapping("/sequence-list")
    public Mono<SequencedCollection<String>> seqSerialization() {
        SequencedCollection<String> sequencedCollection = new ArrayList<>();
        sequencedCollection.addFirst("ab");
        return Mono.just(sequencedCollection)
                .map(seq -> {
                    seq.add("c");
                    seq.add("d");
                    seq.addLast("e");
                    System.out.println(seq.getLast());
                    System.out.println(seq.getFirst());
                    seq.removeFirst();
                    seq.removeLast();
                    return seq.reversed();
                });
    }

    @RequestMapping("/sequence-set")
    public Mono<SequencedSet<String>> seqSetSerialization() {
        SequencedSet<String> sequencedSet = new LinkedHashSet<>();
        sequencedSet.addFirst("ab");
        return Mono.just(sequencedSet)
                .map(seq -> {
                    seq.add("c");
                    seq.add("d");
                    seq.addLast("e");
                    System.out.println(seq.getLast());
                    System.out.println(seq.getFirst());
                    seq.removeFirst();
                    seq.removeLast();
                    return seq.reversed();
                });
    }

    @RequestMapping("/sequence-map")
    public Mono<SequencedMap<Integer, String>> seqMapSerialization() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(2, "b");
        sequencedMap.put(3, "c");
        return Mono.just(sequencedMap)
                .map(mp -> {
                    mp.putFirst(1,"a");
                    mp.putLast(4,"d");
                    System.out.println("Last Entry: "+mp.lastEntry().getKey()+":"+mp.lastEntry().getValue());
                    System.out.println("First Entry: "+mp.firstEntry().getKey()+":"+mp.firstEntry().getValue());
                    mp.pollLastEntry();
                    mp.pollFirstEntry();
                    return mp.reversed();
                });
    }

    @RequestMapping("/sequence-map/keyset")
    public Mono<SequencedSet<Integer>> seqKeySetFromMap() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(2, "b");
        sequencedMap.put(3, "c");
        return Mono.just(sequencedMap.reversed().sequencedKeySet());
    }

    @RequestMapping("/sequence-map/valueset")
    public Mono<SequencedCollection<String>> seqValueSetFromMap() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(2, "b");
        sequencedMap.put(3, "c");
        return Mono.just(sequencedMap.reversed().sequencedValues());
    }

    @RequestMapping("/sequence-map/entryset")
    public Mono<SequencedSet<Map.Entry<Integer, String>>> seqEntrySetFromMap() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(2, "b");
        sequencedMap.put(3, "c");
        return Mono.just(sequencedMap.reversed().sequencedEntrySet());
    }

    // Should throw UnsupportedOperationException
    @RequestMapping("/sequence-map/unmod")
    public Mono<SequencedMap<Integer, String>> seqMapUnModifiable() {
        SequencedMap<Integer, String> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put(2, "b");
        sequencedMap.put(3, "c");
        SequencedMap<Integer, String> unmodSequencedMap = Collections.unmodifiableSequencedMap(sequencedMap);
        return Mono.just(unmodSequencedMap)
                .map(mp -> {
                    mp.putFirst(1,"a");
                    return mp;
                });
    }

    @RequestMapping("/sequence-list/unmod")
    public Mono<SequencedCollection<String>> seqListUnmodifiable() {
        SequencedCollection<String> sequencedCollection = new ArrayList<>();
        sequencedCollection.addFirst("ab");
        sequencedCollection.add("c");
        sequencedCollection.add("d");
        sequencedCollection.addLast("e");
        SequencedCollection<String> unmodSequencedCollection = Collections.unmodifiableSequencedCollection(sequencedCollection);
        return Mono.just(unmodSequencedCollection)
                .map(seq -> {
                    seq.addFirst("xyz");
                    return seq;
                });
    }

    @RequestMapping("/sequence-set/unmod")
    public Mono<SequencedSet<String>> seqSetUnmodifiable() {
        SequencedSet<String> sequencedSet = new LinkedHashSet<>();
        sequencedSet.addFirst("ab");
        sequencedSet.add("c");
        sequencedSet.add("d");
        sequencedSet.addLast("e");
        SequencedSet<String> unmodSeqSet = Collections.unmodifiableSequencedSet(sequencedSet);
        return Mono.just(unmodSeqSet)
                .map(seq -> {
                    seq.addFirst("xyz");
                    return seq;
                });
    }
}
