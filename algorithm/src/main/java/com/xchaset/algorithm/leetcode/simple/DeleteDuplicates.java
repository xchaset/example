package com.xchaset.algorithm.leetcode.simple;

//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
//
// 示例 1:
//
//输入: 1->1->2
//输出: 1->2
//
//
// 示例 2:
//
// 输入: 1->1->2->3->3
//输出: 1->2->3
// Related Topics 链表
public class DeleteDuplicates {

    public static void main(String[] args) {
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3,new ListNode(4)))));
        ListNode listNode1 = deleteDuplicates.deleteDuplicates2(listNode);
        foreachListNode(listNode);
        System.out.println();
        foreachListNode(listNode1);
    }

    private static void foreachListNode(ListNode listNode) {
        ListNode next = listNode.next;
        System.out.print(listNode.val);
        if (next != null) {
            foreachListNode(next);
        }
    }

    // 1-1-2-3-3
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates(head.next);
        if (head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }

    // 1-1-2-3-3
    // 1-2-3-3
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null){
            if (current.val == current.next.val) {
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }
        return head;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
