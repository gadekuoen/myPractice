package cn.wjqixige.Recursion;

/**
 * 删除链表中等于给定值val的所有元素。
 *
 * 示例：
 * 给定：1 ——> 2 ——> 6 ——> 3 ——> 4 ——> 5 ——> 6, val=6
 * 返回：1 ——> 2 ——> 3 ——> 4 ——> 5
 */
public class L203_Solution {


    //不涉及虚拟头结点
    public ListNode removeElements(ListNode head, int val){
        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null){
            return null;
        }

        ListNode prev = head;
        while (prev.next != null){
            if (prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return head;
    }


    public ListNode removeElements1(ListNode head, int val){


        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }


}
