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
                    seq.removeFirst();
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
                    seq.removeFirst();
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
                    System.out.println(mp.lastEntry().getKey()+":"+mp.lastEntry().getValue());
                    mp.pollLastEntry();
                    return mp.reversed();
                });
    }
}
