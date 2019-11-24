package amotoma3.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Palindromic Tree
 *
 * @see <a href="https://math314.hateblo.jp/entry/2016/12/19/005919">link</a>
 */
public class PalindromicTree {
    private List<Node> nodeList;
    private StringBuilder sb;
    private int activeIdx;

    public PalindromicTree() {
        nodeList = new ArrayList<>();
        sb = new StringBuilder();
        Node sizeM1 = new Node(0, -1, 0);
        Node size0 = new Node(0, 0, 0);
        nodeList.add(sizeM1);
        nodeList.add(size0);
        activeIdx = 0;
    }

    public int getActiveIdx() {
        return activeIdx;
    }

    public Node getNode(int id) {
        return nodeList.get(id);
    }

    public void add(char ch) {
        sb.append(ch);
        int a = findPrevPalindromeIdx(activeIdx);
        activeIdx = nodeList.get(a).link.getOrDefault(ch, nodeList.size());
        if (nodeList.get(a).link.containsKey(ch)) {
            activeIdx = nodeList.get(a).link.get(ch);
            nodeList.get(activeIdx).count++;
            return;
        }
        nodeList.get(a).link.put(ch, nodeList.size());

        Node node = new Node(1, nodeList.get(a).len + 2, 1);
        if (node.len > 1) {
            int b = findPrevPalindromeIdx(nodeList.get(a).suffixLink);
            node.suffixLink = nodeList.get(b).link.get(ch);
        }
        nodeList.add(node);
    }

    public int size() {
        return nodeList.size();
    }

    public int[] buildFrequency() {
        int[] ret = new int[nodeList.size()];
        for (int i = nodeList.size() - 1; i > 0; i--) {
            ret[i] += nodeList.get(i).count;
            ret[nodeList.get(i).suffixLink] += ret[i];
        }
        return ret;
    }

    public String debugIdToString(int id) {
        if (id == 0) return "(-1)";
        if (id == 1) return "(0)";

        StringBuilder chs = new StringBuilder();
        debugIdToStringDFS(0, id, chs);
        debugIdToStringDFS(1, id, chs);

        int start = chs.length() - 1;
        if (nodeList.get(id).len % 2 == 1) start--;
        for (int i = start; i >= 0; i--) {
            chs.append(chs.charAt(i));
        }
        return chs.toString();
    }

    private int findPrevPalindromeIdx(int nodeId) {
        int pos = sb.length() - 1;
        while (true) {
            int oppositeSideIdx = pos - 1 - nodeList.get(nodeId).len;
            if (oppositeSideIdx >= 0 && sb.charAt(oppositeSideIdx) == sb.charAt(pos)) break;
            nodeId = nodeList.get(nodeId).suffixLink;
        }
        return nodeId;
    }

    private boolean debugIdToStringDFS(int v, int id, StringBuilder chs) {
        if (v == id) return true;
        for (char ch : nodeList.get(v).link.keySet()) {
            if (debugIdToStringDFS(nodeList.get(v).link.get(ch), id, chs)) {
                chs.append(ch);
                return true;
            }
        }
        return false;
    }

    private class Node {
        Map<Character, Integer> link;
        int suffixLink;
        int len;
        int count;

        Node(int suffixLink, int len, int count) {
            this.link = new TreeMap<>();
            this.suffixLink = suffixLink;
            this.len = len;
            this.count = count;
        }
    }
}
