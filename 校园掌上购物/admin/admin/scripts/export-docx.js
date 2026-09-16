const fs = require('fs');
const path = require('path');
const htmlToDocx = require('html-to-docx');

const htmlContent = fs.readFileSync(path.join(__dirname, '../../../核心代码.html'), 'utf8');

const outputPath = path.join(__dirname, '../../../核心代码.docx');

async function generateDocx() {
    try {
        const docxBuffer = await htmlToDocx(htmlContent, null, {
            orientation: 'portrait',
            margins: {
                top: 1440,
                right: 1440,
                bottom: 1440,
                left: 1440,
            },
        });
        
        fs.writeFileSync(outputPath, docxBuffer);
        console.log('Word文档已成功导出到:', outputPath);
    } catch (error) {
        console.error('导出Word文档失败:', error);
        process.exit(1);
    }
}

generateDocx();