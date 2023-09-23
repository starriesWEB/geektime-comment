import request from '@/utils/request'

export function getCommentApi(params) {
    return request({
        url: '/serv/v1/comments',
        method: 'post',
        data: params
    })
}

export function getArticleApi(params) {
    return request({
        url: '/serv/v1/column/articles',
        method: 'post',
        data: params
    })
}
